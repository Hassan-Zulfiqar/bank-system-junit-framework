import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({
        "com.bank.service",
        "com.bank.model"
})
public class TestSuite {
}