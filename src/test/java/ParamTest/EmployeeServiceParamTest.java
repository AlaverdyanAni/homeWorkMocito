package ParamTest;

import com.example.homeworkmocito.Exception.EmployeeAlreadyAddedException;
import com.example.homeworkmocito.Exception.EmployeeFullNameException;
import com.example.homeworkmocito.Exception.EmployeeNotFoundException;
import com.example.homeworkmocito.Service.Employee;
import com.example.homeworkmocito.Service.EmployeeService;
import com.example.homeworkmocito.Service.EmployeeServiceImpl;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class EmployeeServiceParamTest {
    private final EmployeeService out = new EmployeeServiceImpl();

    public static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("Елена", "Фанина", 1, 66750),
                Arguments.of("Римма", "Зорина", 2, 75200),
                Arguments.of("Елизавета", "Юрьевна", 3, 76450));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void negativeTestForAdd1(String name, String surName, int depId, double salary) {
        Employee expected = new Employee(name, surName, depId, salary);
        assertThat(out.getAllEmployees().isEmpty());
        out.addEmployee(name, surName, depId, salary);
        assertThat(out.getAllEmployees())
                .hasSize(1)
                .containsExacly(expected);
        assertThat(out.findEmployee(expected.getName(), expected.getSurName()))
                .isNotNull()
                .isEqualTo(expected);
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> out.addEmployee(name, surName, depId, salary));
    }

    @Test
    public void negativeTestForAdd2() {
        assertThatExceptionOfType(EmployeeFullNameException.class)
                .isThrownBy(() -> out.addEmployee(null, "Фанина", 1, 66750));
        assertThatExceptionOfType(EmployeeFullNameException.class)
                .isThrownBy(() -> out.addEmployee("Ри2h-мма", "Зорина", 2, 75200));
        assertThatExceptionOfType(EmployeeFullNameException.class)
                .isThrownBy(() -> out.addEmployee("Елизавета", "Юрьевна?", 3, 76450));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void negativeTestForRemove(String name, String surName, int depId, double salary) {
        assertThat(out.getAllEmployees().isEmpty());
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> out.removeEmployee("test", "test"));

        Employee expected = new Employee(name, surName, depId, salary);
        out.addEmployee(name, surName, depId, salary);
        assertThat(out.getAllEmployees())
                .hasSize(1)
                .containsExacly(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)/////////
                .isThrownBy(() -> out.removeEmployee("test", "test"));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void positiveTestForRemove(String name, String surName, int depId, double salary) {
        assertThat(out.getAllEmployees().isEmpty());
        Employee expected = new Employee(name, surName, depId, salary);
        out.addEmployee(name, surName, depId, salary);

        assertThat(out.getAllEmployees())
                .hasSize(1)
                .containsExacly(expected);
        assertThat(out.removeEmployee(name, surName)).isEqualTo(expected);
        assertThat(out.getAllEmployees().isEmpty());
    }

    @ParameterizedTest
    @MethodSource("params")
    public void negativeTestForFind(String name, String surName, int depId, double salary) {
        assertThat(out.getAllEmployees().isEmpty());
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> out.findEmployee("test", "test"));

        Employee expected = new Employee(name, surName, depId, salary);
        out.addEmployee(name, surName, depId, salary);
        assertThat(out.getAllEmployees())
                .hasSize(1)
                .containsExacly(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> out.findEmployee("test", "test"));
    }

    @ParameterizedTest
    @MethodSource("params")
    public void positiveTestForRemove(String name, String surName, int depId, double salary) {
        assertThat(out.getAllEmployees().isEmpty());

        Employee expected = new Employee(name, surName, depId, salary);
        out.addEmployee(name, surName, depId, salary);
        assertThat(out.addEmployee(name, surName, depId, salary)).isEqualTo(expected);
        assertThat(out.findEmployee(name, surName)).isEqualTo(expected);
        assertThat(out.getAllEmployees()).hasSize(1);
    }
}
