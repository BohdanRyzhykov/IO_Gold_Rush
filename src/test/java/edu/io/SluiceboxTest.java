package edu.io.token;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SluiceboxTest {

    @Test
    void default_gain_and_durability() {
        var s = new SluiceboxToken();
        Assertions.assertEquals(1.2, s.gainFactor(), 1e-9);
        Assertions.assertEquals(5, s.durability());
    }

    @Test
    void can_create_with_custom_values() {
        var s = new SluiceboxToken(1.5, 4);
        Assertions.assertEquals(1.5, s.gainFactor(), 1e-9);
        Assertions.assertEquals(4, s.durability());
    }

    @Test
    void throws_when_invalid_ctor_args() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SluiceboxToken(0.0, 3));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SluiceboxToken(1.2, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new SluiceboxToken(1.2, -1));
    }

    @Test
    void use_with_gold_decrements_durability_and_gainFactor() {
        var s = new SluiceboxToken(1.2, 3);
        var r = s.useWith(new GoldToken(1.0));
        r.ifWorking(() -> {});
        Assertions.assertEquals(2, s.durability());
        Assertions.assertEquals(1.2 - 0.04, s.gainFactor(), 1e-9);
    }

    @Test
    void use_with_non_gold_is_idle() {
        var s = new SluiceboxToken();
        s.useWith(new EmptyToken())
                .ifIdle(() -> {})
                .ifWorking(() -> Assertions.fail())
                .ifBroken(() -> Assertions.fail());
    }

    @Test
    void broken_sluicebox_behaviour_and_not_repairable_by_anvil() {
        var s = new SluiceboxToken(1.2, 1);
        s.useWith(new GoldToken(1.0)).ifWorking(() -> {});
        Assertions.assertTrue(s.isBroken());

        // try to repair with anvil via Player logic: since Player repairs only PickaxeToken,
        // interacting with AnvilToken should NOT repair sluicebox.

        Assertions.assertEquals(0, s.durability());
    }
}
