//package com.gson.jmockit.tutorial.domain;
//
//import org.junit.*;
//import org.junit.Test;
//import org.junit.rules.*;
//
//import static com.gson.jmockit.tutorial.persistence.Database.persist;
//import static org.junit.Assert.*;
//
//import mockit.*;
//
//import org.apache.commons.mail.*;
//
///**
// * http://jmockit.github.io/tutorial/Introduction.html#anExample
// */
//public final class MyBusinessServiceTest
//{
//   @Rule public final ExpectedException thrown = ExpectedException.none();
//
//   @Tested final EntityX data = new EntityX(1, "abc", "someone@somewhere.com");
//   @Tested(fullyInitialized = true) MyBusinessService businessService;
//   @Mocked SimpleEmail anyEmail;
//
//   @org.junit.Test
//   public void doBusinessOperationXyz() throws Exception {
//      EntityX existingItem = new EntityX(1, "AX5", "abc@xpta.net");
//      persist(existingItem);
//
//      businessService.doBusinessOperationXyz();
//
//      assertNotEquals(0, data.getId()); // implies "data" was persisted
//      new Verifications() {{ anyEmail.send(); times = 1; }};
//   }
//
//   @Test
//   public void doBusinessOperationXyzWithInvalidEmailAddress() throws Exception {
//      String email = "invalid address";
//      data.setCustomerEmail(email);
//      new Expectations() {{ anyEmail.addTo(email); result = new EmailException(); }};
//      thrown.expect(EmailException.class);
//
//      businessService.doBusinessOperationXyz();
//   }
//}
