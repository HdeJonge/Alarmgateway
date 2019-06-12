package com.bprocare.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bprocare.service.LocationService;
import com.bprocare.web.LocationController;

@RunWith(SpringRunner.class)
@WebMvcTest(LocationController.class)
public class ApplicationTest {

	
    @Autowired
    private MockMvc mockMvc;	
	
    @MockBean
    private LocationService locationService;    
    
    @Test
    public void main() throws Exception {

        
    }

}
