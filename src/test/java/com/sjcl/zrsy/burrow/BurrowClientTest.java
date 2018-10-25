package com.sjcl.zrsy.burrow;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class BurrowClientTest {

    @Test
    public void testCall() throws IOException {


        ContractFactory contractFactory = new ContractFactory("CD0DC3A187DCB89303E202BF60F9E48199CEBB9D842C9F459621E35C1CE8444538B858ECB503D4920F2BD7A4EFC7080F619E961FDA1A6ADCFB2BEEED744954CE");
        String contractAddress="92D0F6F4086C8E716AED5EC02D42CCD819219B00";

        String abi="[\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_interfaceID\",\n" +
                "\t\t\t\t\"type\": \"bytes4\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"supportsInterface\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"bool\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"name\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_to\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_tokenId\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"approve\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_tokenId\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"isSale\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": true,\n" +
                "\t\t\"stateMutability\": \"payable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_newGovernment\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"setGovernment\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"totalSupply\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"PIG_BASIC_COUNT\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_from\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_to\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_tokenId\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"transferFrom\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"slaughterhouseAdddress\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"unpause\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"marketAdddress\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_weight\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_tokenId\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_breed\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"createPigSale\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_pigID\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"paused\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"bool\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_seller\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_buyer\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_pigID\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_price\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"createSell\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"withdrawBalance\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_seller\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_buyer\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_tokenId\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_price\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"createSale\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_tokenId\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"ownerOf\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"owner\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_newMarket\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"setMarket\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_owner\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"balanceOf\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"count\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"pigCreatedCount\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_id\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"getPig\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"currentAddress\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"birthTime\",\n" +
                "\t\t\t\t\"type\": \"uint64\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"breed\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"weight\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"NFT\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"pause\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_owner\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"tokensOfOwner\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"ownerTokens\",\n" +
                "\t\t\t\t\"type\": \"uint256[]\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"withdrawSaleBalances\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"owner\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"symbol\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_newSlaughterhouse\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"setSlaughterhouse\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_to\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_tokenId\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"transfer\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"governmentAdddress\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"pigIndexToOwner\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"transaction\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"ownershipTokenCount\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_newFarm\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"setFarm\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_tokenId\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"getSale\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"seller\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"buyer\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"price\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"time\",\n" +
                "\t\t\t\t\"type\": \"uint64\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_newLogistics\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"setLogistics\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"pigIndexToApproved\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"newOwner\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"transferOwnership\",\n" +
                "\t\t\"outputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"logisticsAdddress\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"name\": \"farmAddress\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"inputs\": [],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"nonpayable\",\n" +
                "\t\t\"type\": \"constructor\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anonymous\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"tokenId\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"price\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"buyer\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"SaleCreated\",\n" +
                "\t\t\"type\": \"event\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anonymous\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"tokenId\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"price\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"buyer\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"SaleSuccessful\",\n" +
                "\t\t\"type\": \"event\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anonymous\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"tokenId\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"SaleCancelled\",\n" +
                "\t\t\"type\": \"event\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anonymous\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"from\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"to\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"tokenId\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"Transfer\",\n" +
                "\t\t\"type\": \"event\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anonymous\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"owner\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"approved\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"tokenId\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"Approval\",\n" +
                "\t\t\"type\": \"event\"\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"anonymous\": false,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"pigID\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"owner\",\n" +
                "\t\t\t\t\"type\": \"address\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"birthTime\",\n" +
                "\t\t\t\t\"type\": \"uint64\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"breed\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"indexed\": false,\n" +
                "\t\t\t\t\"name\": \"weight\",\n" +
                "\t\t\t\t\"type\": \"uint256\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"Birth\",\n" +
                "\t\t\"type\": \"event\"\n" +
                "\t}\n" +
                "]";
        PigCore pigCore = contractFactory.getContractObject(PigCore.class, contractAddress, abi);



//        File file = new File("/opt/working/PIE/src/main/java/com/sjcl/zrsy/solidity/abi/pigCore.abi");//定义一个file对象，用来初始化FileReader
//        FileReader reader = new FileReader(file);//定义一个fileReader对象，用来初始化BufferedReader
//        BufferedReader bReader = new BufferedReader(reader);//new一个BufferedReader对象，将文件内容读取到缓存
//        StringBuilder sb = new StringBuilder();//定义一个字符串缓存，将字符串存放缓存中
//        String s = "";
//        while ((s =bReader.readLine()) != null) {//逐行读取文件内容，不读取换行符和末尾的空格
//            sb.append(s);//将读取的字符串添加换行符后累加存放在缓存中
//        }
//        bReader.close();
//        PigCore pigCore = contractFactory.getContractObject(PigCore.class, contractAddress,sb.toString());
        System.out.println(pigCore.createPigSale(BigInteger.valueOf(12), BigInteger.valueOf(1234567980),BigInteger.valueOf(1)));

    }
}
