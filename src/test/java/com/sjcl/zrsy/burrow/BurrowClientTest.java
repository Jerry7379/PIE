package com.sjcl.zrsy.burrow;

import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

public class BurrowClientTest {

    @Test
    public void testCall() throws IOException {

        ContractFactory contractFactory = new ContractFactory("4DAFF16F3729238D278A2D8332A3B1B1769C84685B24C7F7E574C87357724646FA9D5E535F845474800BAD75253C5B6B610B260628A99B88ABDDB77EAD571A36");

        Caculator caculator = contractFactory.getContractObject(Caculator.class, "35F83AB5AE9A4E7F77A81160AD9972E7C66F1108", "[{\n" +
                "    \"constant\": true,\n" +
                "    \"inputs\": [\n" +
                "        {\n" +
                "            \"name\": \"a\",\n" +
                "            \"type\": \"int256\"\n" +
                "        }, {\n" +
                "            \"name\": \"b\",\n" +
                "            \"type\": \"int256\"\n" +
                "        }],\n" +
                "    \"name\": \"add\",\n" +
                "    \"outputs\": [{\n" +
                "        \"name\": \"sum\",\n" +
                "        \"type\": \"int256\"\n" +
                "    }],\n" +
                "    \"type\": \"function\"\n" +
                "}]");


        System.out.println(caculator.add(BigInteger.valueOf(8), BigInteger.valueOf(5)));
    }
}
