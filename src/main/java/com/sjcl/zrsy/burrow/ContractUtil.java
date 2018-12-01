package com.sjcl.zrsy.burrow;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.springframework.lang.Nullable;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ContractUtil {
    private static final String ABI_TYPE_PACKAGE_NAME = "org.web3j.abi.datatypes";
    private static final String ABI_TYPE_GENERATED_PACKAGE_NAME = "org.web3j.abi.datatypes.generated";
    private static final String POINT = ".";

    private BurrowClient burrowClient;
    private String privateKey;
    private String abi;

    public ContractUtil(String privateKey, String abi) {
        this.burrowClient = new BurrowClient();
        this.privateKey = privateKey;
        this.abi = abi;
    }


    public String getData(String methodName, @Nullable Object... args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        JSONObject abiFunction = findAbiFunction(methodName);

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
        return data.substring(2);

    }

    public Object sendTrancation(String address, String data, JSONObject abiFunction) throws IOException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        String encodeReturn = burrowClient.transactAndHold(address, data, privateKey, BigInteger.valueOf(3000), BigInteger.valueOf(100));
        Type obj = FunctionReturnDecoder.decode(encodeReturn, extractPostinvokeOutputs(abiFunction)).get(0);
        return obj.getValue();
    }

    public  JSONObject findAbiFunction(String methodName) {
        JSONArray abiInfo = JSON.parseArray(abi);
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
     *
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
     *
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
     *
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
