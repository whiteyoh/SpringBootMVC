package com.example.restservice.Models;

import com.example.restservice.Exceptions.BlockChainExceptions;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CryptoBlockTest {

    @Autowired
    private TestRestTemplate restTemplate;
    private CryptoBlock cryptoBlock;

    @Test
    public void assertThat_knownHashEqualsCalculatedHash_success() throws BlockChainExceptions {
        String hashTest = "053795A485E2314F791BBFEEE433DB7BB4D948DAB2B9D65651D6153D0DE9038D";
        CryptoBlock cryptoBlock = new CryptoBlock();
        cryptoBlock.setIndex(0);
        cryptoBlock.setPrecedingHash("0");
        cryptoBlock.setData("Opening Block");
        cryptoBlock.setTimestamp("2020-08-11");
        String hash = cryptoBlock.getSHA256Hash(cryptoBlock);
        assertThat(hashTest).isEqualTo(hash);
    }

    @Test
    public void assertThat_knownHashEqualsCalculatedHash_fail() throws BlockChainExceptions {
        String hashTest = "nonsense";
        CryptoBlock cryptoBlock = new CryptoBlock();
        cryptoBlock.setIndex(0);
        cryptoBlock.setPrecedingHash("0");
        cryptoBlock.setData("Opening Block");
        cryptoBlock.setTimestamp("2020-08-11");
        String hash = cryptoBlock.getSHA256Hash(cryptoBlock);
        assertThat(hashTest).isNotEqualTo(hash);
    }

}