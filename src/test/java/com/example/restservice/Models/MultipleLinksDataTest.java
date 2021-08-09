package com.example.restservice.Models;

import com.example.restservice.Exceptions.BlockChainExceptions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MultipleLinksDataTest {

    @Autowired
    private MultipleLinksData multipleLinksData;

    @Test
    public void assertThat_theBlockChainIsValid_success() throws BlockChainExceptions {
        // Given
        ArrayList<SingleLinkData> data = new ArrayList();

        SingleLinkData row = new SingleLinkData();
        row.setIndex("0");
        row.setData("Opening Block");
        row.setHash("053795A485E2314F791BBFEEE433DB7BB4D948DAB2B9D65651D6153D0DE9038D");
        row.setPreviousHash("0");
        row.setTimestamp("2020-08-11");
        data.add(row);

        SingleLinkData row2 = new SingleLinkData();
        row2.setIndex("1");
        row2.setData("this:yes::that:true::sensitive:false");
        row2.setHash("F9150EF1465132A65B4254A08D9796950C892837EA983A4A0A78670859256F13");
        row2.setPreviousHash("053795A485E2314F791BBFEEE433DB7BB4D948DAB2B9D65651D6153D0DE9038D");
        row2.setTimestamp("2020-08-11");
        data.add(row2);
        MultipleLinksData data2 = new MultipleLinksData(data,"181818181818");
        Boolean validateChain = data2.validateBlockChain(data2);
        assertThat(validateChain).isEqualTo(true);
    }

    @Test
    public void assertThat_theBlockChainIsValid_fail() throws BlockChainExceptions {
        // Given
        ArrayList<SingleLinkData> data = new ArrayList();
        // When
        SingleLinkData row = new SingleLinkData();
        row.setIndex("0");
        row.setData("Opening Block");
        row.setHash("053795A485E2314F791BBFEEE433DB7BB4D948DAB2B9D65651D6153D0DE9038D");
        row.setPreviousHash("0");
        row.setTimestamp("2020-08-11");
        data.add(row);

        SingleLinkData row2 = new SingleLinkData();
        row2.setIndex("1");
        row2.setData("this:yes::that:true::sensitive:false");
        row2.setHash("F9150EF1465132A65B4254A08D9796950C892837EA983A4A0A78670859256F13");
        row2.setPreviousHash("nonsense");
        row2.setTimestamp("2020-08-11");
        data.add(row2);
        MultipleLinksData data2 = new MultipleLinksData(data,"181818181818");
        Boolean validateChain = data2.validateBlockChain(data2);

        // Then
        assertThat(validateChain).isEqualTo(false);
    }

}