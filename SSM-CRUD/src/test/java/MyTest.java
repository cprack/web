import com.cprack.dao.DepartmentMapper;
import com.cprack.dao.EmployeeMapper;
import com.cprack.domain.Department;
import com.cprack.domain.Employee;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 使用Spring的单元测试
 * @ContextConfiguration指定Spring配置文件的位置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springMVC.xml"})
public class MyTest {

    MockMvc mockMvc;
    @Autowired
    WebApplicationContext context;

    @Before
    public void initMockMvc(){
       mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private SqlSession sqlSession;

    @Test
    public void generator() throws Exception{
        List<String> warning = new ArrayList<String>();
        File configFile = new File("./src/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warning);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warning);
        myBatisGenerator.generate(null);
    }

    /**
     * DepartmentMapper的测试
     */
    @Test
    public void departmentTest01(){
        //插入几个部门
        departmentMapper.insert(new Department(null, "开发部"));
        departmentMapper.insert(new Department(null, "测试部"));
    }

    /**
     * EmployeeMapper的测试
     */
    @Test
    public void employeeTest01(){
        //插入几个员工
        employeeMapper.insert(new Employee(null, "Jerry", "女", "Jerry@qq.com", 1));
        employeeMapper.insert(new Employee(null, "Tom", "男", "Tom@qq.com", 2));
    }

    @Test
    public void employeeTest02(){
        //批量插入1000个员工
        for(int i = 0;i < 1000;i++){
            String uid = UUID.randomUUID().toString().substring(0,5);
            employeeMapper.insert(new Employee(null, uid, "男", uid + "@qq.com", 2));
        }
    }

    /**
     * spring提供的模拟请求测试
     */
    @Test
    public void testPage() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emp").param("pn","1")).andReturn();
        MockHttpServletRequest request = result.getRequest();
        PageInfo attribute = (PageInfo) request.getAttribute("pageInfo");
        System.out.println(attribute.toString());
    }
}
