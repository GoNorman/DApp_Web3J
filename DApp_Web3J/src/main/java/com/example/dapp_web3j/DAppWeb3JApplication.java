package com.example.dapp_web3j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.web3j.protocol.Web3j;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.http.HttpService;


import java.util.concurrent.ExecutionException;



@SpringBootApplication
public class DAppWeb3JApplication {

    Web3j web3j = Web3j.build(new HttpService("https://mainnet.infura.io/v3/d21e9050c0a74e80987c0813c07d0558"));

    Credentials credentials = Credentials.create("cb4dad1857a5b618c3136bc270a689db618a6f04d4e05e9325a110eb8061a90d");

    //d21e9050c0a74e80987c0813c07d0558
    public static final String DEFAULT_ADDRESS = "0x281055afc982d96fab65b3a49cac8b878184cb16";
    public static void main(String[] args) {

        SpringApplication.run(DAppWeb3JApplication.class, args);

    }

    public EthBlockNumber getBlockNumber() throws ExecutionException, InterruptedException {
        EthBlockNumber result = new EthBlockNumber();
        result = this.web3j.ethBlockNumber().sendAsync().get();

        return result;
    }

    public EthGetTransactionCount getTransactionCount() throws ExecutionException, InterruptedException {
        EthGetTransactionCount result = new EthGetTransactionCount();
        result = this.web3j.ethGetTransactionCount(DEFAULT_ADDRESS, DefaultBlockParameter.valueOf("latest")).
                sendAsync().get();
        return result;
    }

    public EthGetBalance getEthBalance() throws  ExecutionException, InterruptedException {
        EthGetBalance result = new EthGetBalance();
        result = this.web3j.ethGetBalance(DEFAULT_ADDRESS, DefaultBlockParameter.valueOf("letest")).
                sendAsync().get();
        return result;
    }

}
