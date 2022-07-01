package com.example.library;

import com.example.library.service.ItemServiceTest;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
//        ItemControllerTest.class,
        ItemServiceTest.class
})
public class UnitTestSuite {
}
