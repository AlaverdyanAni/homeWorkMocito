package ParamTest;

import com.example.homeworkmocito.Exception.EmployeeNotFoundException;
import com.example.homeworkmocito.Service.DepartamentService;
import com.example.homeworkmocito.Service.DepartamentServiceImpl;
import com.example.homeworkmocito.Service.Employee;
import com.example.homeworkmocito.Service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartamentServiceParamTest {
    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartamentServiceImpl departamentService;

    @BeforeEach
    public void beforEach(){
        List <Employee> employees=List.of(
                new Employee( "Елена", "Фанина",1,66750),
                new Employee( "Римма", "Зорина",2,75200),
                new Employee( "Елизавета", "Юрьевна",3,76450),
                new Employee( "Елизар", "Гарин",3,78200),
                new Employee( "Яна", "Балтабева",4, 86380)
                 );
        when(employeeService.getAllEmployees()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("emloyeeWithMaxSalaryParams")
    public void employeeWithMaxSalaryPositiveTest(int depId,Employee expected){
        assertThat(departamentService.getEmployeeWithMaxSalaryFromDepartament(depId)).isEqualsTo(expected);
    }
   @Test
   public void employeeWithMaxSalaryNegativeTest(){
        assertThatExceptionOfType(EmployeeNotFoundException.class)
       .isThrownBy(()->departamentService.getEmployeeWithMaxSalaryFromDepartament(3));
   }


    @ParameterizedTest
    @MethodSource("emloyeeWithMinSalaryParams")
    public void employeeWithMinSalaryPositiveTest(int depId,Employee expected){
        assertThat(departamentService.getEmployeeWithMinSalaryFromDepartament(depId).isEqualsTo(expected));
    }
    @Test
    public void employeeWithMinSalaryNegativeTest(){
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(()->departamentService.getEmployeeWithMinSalaryFromDepartament(3));
    }
    @ParameterizedTest
    @MethodSource("employeesFromDepartamentParams")
    public void employeesFromDepartmentTest(int depId,List<Employee> expected){
        assertThat(departamentService.getAllEmployeesByDepartaments(depId)).containsExaclyElementsOf(expected);
    }
    @Test
    public void employeesByDepartamentTest(){
        assertThat(departamentService.getAllEmployeesByDepartaments()).containsExaclyElementsOf(List.of(
                new Employee( "Елена", "Фанина",1,66750),
                new Employee( "Римма", "Зорина",2,75200),
                new Employee( "Елизавета", "Юрьевна",3,76450),
                new Employee( "Елизар", "Гарин",3,78200),
                new Employee( "Яна", "Балтабева",4, 86380)));
    }
    public static Stream<Arguments> emloyeeWithMaxSalaryParams(){
        return Stream.of(
                Arguments.of( 1,new Employee( "Елена", "Фанина",1,66750)),
                  Arguments.of(2, new Employee( "Римма", "Зорина",2,75200))
        );
    }
    public static Stream<Arguments> emloyeeWithMinSalaryParams(){
        return Stream.of(
                Arguments.of(1, new Employee( "Елена", "Фанина",1,66750)),
                   Arguments.of( 2,new Employee( "Римма", "Зорина",2,75200))
        );
    }
    public static Stream<Arguments> employeesFromDepartamentParams(){
        return  Stream.of(
                Arguments.of( 1,new Employee( "Елена", "Фанина",1,66750)),
                Arguments.of( 2,new Employee( "Римма", "Зорина",2,75200)),
                Arguments.of(3,Collections.emptyList())
        );
    }
}
