package lib.config;

import lib.controller.CheckInputAlpha;
import lib.controller.IOperation;
import lib.controller.Operation;
import lib.dao.Crud;
import lib.dao.DBCrud;
import lib.dao.ICrud;
import lib.view.ControlConsole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("lib")
public class FileContext {

    @Bean
    public ICrud CrudConfig() {
        return new Crud();
    }

    @Bean
    public IOperation OperationConfig() {
        return new Operation();
    }

    @Bean
    public CheckInputAlpha InputConfig() {
        return new CheckInputAlpha();
    }

    @Bean
    public ControlConsole ConsoleConfig() {
        return new ControlConsole();
    }
}