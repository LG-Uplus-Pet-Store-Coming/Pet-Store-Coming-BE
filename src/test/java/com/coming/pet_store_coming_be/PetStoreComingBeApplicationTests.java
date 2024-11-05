package com.coming.pet_store_coming_be;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.amazonaws.services.s3.AmazonS3;

@SpringBootTest
class PetStoreComingBeApplicationTests {

	@MockBean
    private AmazonS3 amazonS3;  // Mocking AmazonS3

	@Test
	void contextLoads() {
		// Mock behavior if necessary
        when(amazonS3.getBucketLocation("coming-s3-bucket")).thenReturn("ap-southeast-2");

        // Add assertions or further testing logic here
	}

}
