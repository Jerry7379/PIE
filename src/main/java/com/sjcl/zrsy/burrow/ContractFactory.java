package com.sjcl.zrsy.burrow;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;

import java.io.IOException;
import java.lang.reflect.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ContractFactory {

    private static final String ABI_TYPE_PACKAGE_NAME = "org.web3j.abi.datatypes";
    private static final String ABI_TYPE_GENERATED_PACKAGE_NAME = "org.web3j.abi.datatypes.generated";
    private static final String POINT = ".";

    private BurrowClient burrowClient;
    private String privateKey;

    public ContractFactory(BurrowClient client, String privateKey) {
        this.burrowClient = client;
        this.privateKey = privateKey;
    }

    public ContractFactory(String privateKey) {
        this(new BurrowClient(), privateKey);
    }


    public <T> T getContractObject(Class<T> contractClass, final String address, final String abi) {
        return (T) Proxy.newProxyInstance(ContractFactory.class.getClassLoader(), new Class[]{contractClass}, new ContractInvocationHandler(abi, address));
    }

    /***
     * 创建一个与代理对象相关的ContractInvocationHandler类
     */
    private class ContractInvocationHandler implements InvocationHandler {
        //合约的abi字符串
        private String abi;
        //合约地址
        private String address;

        /**
         * 构造函数
         * @param abi
         * @param address
         */
        public ContractInvocationHandler(String abi, String address) {
            this.abi = abi;
            this.address = address;
        }

        /**
         * 重写了InvocationHandler中的invoke方法
         * @param proxy
         * @param method
         * @param args
         * @return
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            JSONArray abiInfo = JSON.parseArray(abi);
            String methodName = method.getName();

            JSONObject abiFunction = findAbiFunction(abiInfo, methodName);

            if (abiFunction == null) {
                throw new RuntimeException("");
            }
            //获取方法名
            String functionName = abiFunction.getString("name");
            //获取方法参数类型和值的集合
            List<Type> inputs = extractInputs(abiFunction, args);
            //提取返回值
            List<TypeReference<?>> outputs = extractPreinvokeOutputs(abiFunction);
            Function function = new Function(functionName, inputs, outputs);

            String data = FunctionEncoder.encode(function);
            data = data.substring(2);

            boolean constant = abiFunction.getBoolean("constant");//TODO

            return invokeContractFunction(constant, abiFunction, data);
        }

        private Object invokeContractFunction(boolean constant, JSONObject abiFunction, String data) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {
            String encodeReturn;
            if (constant) {
                encodeReturn = burrowClient.call(address, data);
            } else {
                encodeReturn = burrowClient.transactAndHold(address, data, privateKey, BigInteger.valueOf(3000), BigInteger.valueOf(100));
            }
            Type obj = FunctionReturnDecoder.decode(encodeReturn, extractPostinvokeOutputs(abiFunction)).get(0);
            return obj.getValue();
        }

        /**
         * 从abi中Json数组中找到与methodName相同的方法
         * @param abiInfo   json数组
         * @param methodName  需要调用的方法
         * @return 返回abi中方法的json
         */
        private JSONObject findAbiFunction(JSONArray abiInfo, String methodName) {
            for (int i = 0; i < abiInfo.size(); i++) {
                JSONObject abiFunction = abiInfo.getJSONObject(i);
                String abiFunctionName = abiFunction.getString("name");
                if (methodName.equals(abiFunctionName)) {
                    return abiFunction;
                }
            }
            return null;
        }

        /**
         * 获取方法的参数类型和值
         * @param abiFunction
         * @param args
         * @return
         * @throws ClassNotFoundException
         * @throws NoSuchMethodException
         * @throws IllegalAccessException
         * @throws InvocationTargetException
         * @throws InstantiationException
         */
        private List<Type> extractInputs(JSONObject abiFunction, Object[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
            JSONArray abiFunctionInputs = abiFunction.getJSONArray("inputs");
            List<Type> solidityFunctionParams = new ArrayList<>();
            for (int j = 0; j < abiFunctionInputs.size(); j++) {
                JSONObject abiFunctionParam = abiFunctionInputs.getJSONObject(j);
                Class javaParamClass = getTypeClass(abiFunctionParam);
                Constructor paramConstructor = javaParamClass.getConstructor(args[j].getClass());
                Type solidityFunctionParam = (Type) paramConstructor.newInstance(args[j]);
                solidityFunctionParams.add(solidityFunctionParam);
            }
            return solidityFunctionParams;
        }

        /**
         * 提取返回值的类型和值的集合
         * @param abiFunction
         * @return
         * @throws ClassNotFoundException
         * @throws IllegalAccessException
         * @throws InstantiationException
         */
        private List<TypeReference<?>> extractPreinvokeOutputs(JSONObject abiFunction) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
            JSONArray abiFunctionOutputs = abiFunction.getJSONArray("outputs");
            List<TypeReference<?>> solidityFunctionReturns = new ArrayList<>();
            for (int j = 0; j < abiFunctionOutputs.size(); j++) {
                JSONObject abiFunctionOutput = abiFunctionOutputs.getJSONObject(j);
                Class javaOutputClass = getTypeClass(abiFunctionOutput);
                TypeReference output = TypeReference.create(javaOutputClass);
                solidityFunctionReturns.add(output);
            }
            return solidityFunctionReturns;
        }

        private List<TypeReference<Type>> extractPostinvokeOutputs(JSONObject abiFunction) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
            JSONArray abiFunctionOutputs = abiFunction.getJSONArray("outputs");
            List<TypeReference<Type>> solidityFunctionReturns = new ArrayList<>();
            for (int j = 0; j < abiFunctionOutputs.size(); j++) {
                JSONObject abiFunctionOutput = abiFunctionOutputs.getJSONObject(j);
                Class javaOutputClass = getTypeClass(abiFunctionOutput);
                TypeReference output = TypeReference.create(javaOutputClass);
                solidityFunctionReturns.add(output);
            }
            return solidityFunctionReturns;
        }

        /**
         * 获取方法参数的类型
         * @param put
         * @return
         * @throws ClassNotFoundException
         */
        private Class getTypeClass(JSONObject put) throws ClassNotFoundException {
            String abiFunctionType = put.getString("type");

            String javaParamType;
            char firstChar = Character.toUpperCase(abiFunctionType.charAt(0));
            if (abiFunctionType.length() == 1) {
                javaParamType = firstChar + "";
            } else {
                javaParamType = firstChar + abiFunctionType.substring(1);
            }
            Class javaClass;
            try {
                javaClass = Class.forName(ABI_TYPE_PACKAGE_NAME + POINT + javaParamType);
            } catch (ClassNotFoundException e) {
                javaClass = Class.forName(ABI_TYPE_GENERATED_PACKAGE_NAME + POINT + javaParamType);
            }
            return javaClass;
        }
    }
}
