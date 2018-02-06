package a1;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


@RunWith(Suite.class)
@Suite.SuiteClasses({
    ChessBoardTest.class,
    BishopTest.class,
    KingTest.class,
    KnightTest.class,
    PawnTest.class,
    QueenTest.class,
    RookTest.class
})

public class ChessSuite {
  public static void main (String[] args) {
    Result result = JUnitCore.runClasses(ChessSuite.class);

    for (Failure fail : result.getFailures())
        System.out.println(fail.getTrace());

    System.out.println(result.wasSuccessful());
  }
}
