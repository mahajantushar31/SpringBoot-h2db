package com.h2db.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.springboot.h2.Appmain;
import com.springboot.h2.model.CommonUser;
import com.springboot.h2.model.UserRole;
import com.springboot.h2.repo.ProductRepository;
import com.springboot.h2.serv.CommonUserService;
import com.springboot.h2.serv.impl.CommonUserServiceImpl;
/*
@RunWith(SpringRunner.class)
@SpringBootTest(
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	classes = Appmain.class
)*/
//@AutoConfigureMockMvc
// @TestPropertySource(locations = "classpath:application-test.properties")

public class AdminControllerTest {
	
	@Autowired
	 MockMvc mvc;
	
	@Autowired
	CommonUserService commonUserService; //=new CommonUserServiceImpl();
	
	//@Autowired	
	//ProductRepository prodRepository;	
	
		@Test
		public void getHello() throws Exception {
		/*	mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andReturn();
		*/
			List<CommonUser> usrList=new ArrayList<CommonUser>();
			usrList.add(new CommonUser(2,"Test1", "test1", true));//.getUserRoleSet().add(new UserRole(0, "Cust1", "LimitedCust1")	
			usrList.add(new CommonUser(3,"Test2", "test2", true)); // .getUserRoleSet().add(new UserRole(0, "Cust1", "LimitedCust1")						 
									
			CommonUserService commonUserService=mock(CommonUserService.class);
			
			commonUserService.getAllCommonUsers();
			when(commonUserService.getAllCommonUsers()).thenReturn(usrList);
			System.out.println(" TEST RUN SUCCESS ");
					//.andExpect((ResultMatcher) content().string(equalTo("login")));
		}
		
		/*
		@Test
		public void getAPI1() throws Exception {
			mvc.perform(MockMvcRequestBuilders.post("/user/create","").accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect((ResultMatcher) content().string(equalTo("Greetings from Spring Boot!")));
		}
*/
		
		CommonUser userObject=new CommonUser(2,"TestUser1","TestUser1Pass",true);
		// SAVE      	  commonUserService.saveCommonUser(userObject);
		@Test
		public void  chceckSave(){
			userObject.getUserRoleSet().add(new UserRole(1,"Admin","All Access"));
			CommonUser userObject1=commonUserService.saveCommonUser(userObject);
			assertEquals(userObject,userObject1);
			userObject=userObject1;
			System.out.println("SAVE success ");
		}
		
		// getUser by ID  commonUserService.getCommonUserByUserId(userId);
		@Test
		public void  getUser(){
			Integer userId=userObject.getUserId();
			Optional<CommonUser> userObject1=(Optional<CommonUser>) commonUserService.getCommonUserByUserId(userId);
			assertEquals(userObject,userObject1);
			assertEquals(userObject.getUserId(), userObject1.get().getUserId());
			System.out.println("getUser success "+userId);
		}
		
		
		
		// getAll         commonUserService.getAllCommonUsers();
		@Test
		public void  getAllUser(){
			Integer userId=userObject.getUserId();
			List<CommonUser> userObjectList=commonUserService.getAllCommonUsers();
			assertNotNull(userObjectList);
			System.out.println("getUser success "+userObjectList.size());
		}
		
		
		
		//UPDATE          response=commonUserService.updateCommonUser(commonUser);
		public void  updateUser(){
			Integer userId=userObject.getUserId();
			userObject.setUserName(userObject.getUserName()+"_update001");
			CommonUser userObjectUpdated=commonUserService.updateCommonUser(userObject);
			assertNotNull(userObjectUpdated);
			assertEquals(userObject,userObjectUpdated);
			System.out.println("getUser success "+userObjectUpdated.getUserName()+" old "+userObject.getUserName());
		}
		
		
		// DELETE 		  commonUserService.deleteCommonUser(userId);
		public void  deleteUser(){
			Integer userId=userObject.getUserId();
			//userObject.setUserName(userObject.getUserName()+"_update001");
			commonUserService.deleteCommonUser(userId);
			Optional<CommonUser> cmU=commonUserService.getCommonUserByUserId(userId);
			System.out.println(" CMU  "+cmU);
			assertNull(cmU);	
		}
		
}
