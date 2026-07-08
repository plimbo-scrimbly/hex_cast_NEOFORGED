package at.petrak.hexcasting;

import at.petrak.hexcasting.api.casting.math.EulerPathFinder;
import at.petrak.hexcasting.api.casting.math.HexDir;
import at.petrak.hexcasting.api.casting.math.HexPattern;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EulerPathFinderTest {
    @Test
    void findAltDrawingIsDeterministic() {
        var pattern = HexPattern.fromAngles("dadaddwwaadada", HexDir.NORTH_EAST);

        for (long seed = 0; seed < 8; seed++) {
            var alternate = EulerPathFinder.findAltDrawing(pattern, seed);
            assertNotNull(alternate);
            assertEquals(alternate, EulerPathFinder.findAltDrawing(pattern, seed));
        }
    }
}
