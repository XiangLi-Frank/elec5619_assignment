package com.example.demo.repository;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;


@RunWith(Arquillian.class)
public class BuyRepositoryTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BuyRepository.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testPage() {
        //显示第1页每页显示3条
        PageRequest pr = new PageRequest(1, 1);
        //根据年龄进行查询
        try {
            //Page<BuyDTO> buy = BuyRepository.findAllByUsername('a');
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
