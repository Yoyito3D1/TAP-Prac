package Main;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MainFunctionsTest {

    @Test
    void helloWorldTest() throws InterruptedException {
        MainFunctions.testHelloWorld();}

    @Test
    void insultActor() {
        MainFunctions.insultActor();}

    @Test
    void firewallDecorator() throws InterruptedException {
        MainFunctions.firewallDecorator();}

    @Test
    void lambdaFirewallDecorator() throws InterruptedException {
        MainFunctions.lambdaFirewallDecorator();}

    @Test
    void encryptionDecorator() throws InterruptedException {
        MainFunctions.encryptionDecorator();}

    @Test
    void dynamicProxy() throws InterruptedException {
        MainFunctions.dynamicProxy();}

    @Test
    @Order(1)
    void ringActor() throws InterruptedException {
        MainFunctions.ringActor();
        Thread.sleep(595000);
    }

    @Test
    void pimPom() throws InterruptedException {
        MainFunctions.pimPom(20);
        Thread.sleep(11000);
    }

    @Test
    void allTest() throws InterruptedException {
        MainFunctions.allTest();
    }
}