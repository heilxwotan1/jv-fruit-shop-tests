package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import core.basesyntax.db.Storage;
import core.basesyntax.model.Fruit;
import core.basesyntax.operations.Operation;
import core.basesyntax.strategy.OperationStrategy;
import core.basesyntax.strategy.OperationStrategyImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PurchaseOperationTest {
    private static OperationStrategy operationStrategy;
    private static List<FruitTransaction> fruitTransactionList;

    @BeforeAll
    static void beforeAll() {
        operationStrategy = new OperationStrategyImpl();
        fruitTransactionList = new ArrayList<>();
        Storage.storage.clear();
        Storage.storage.put("banana", 400);
    }

    @Test
    void operationProcess_PurchaseOperation_Ok() {
        fruitTransactionList.add(new FruitTransaction(Operation.PURCHASE,
                new Fruit("banana", 75)));
        OperationProcessImpl operationProcess = new OperationProcessImpl(operationStrategy);
        operationProcess.processTransactions(fruitTransactionList);
        assertEquals(Storage.storage.get("banana"), 325);
    }
}