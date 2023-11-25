package org.insurgencedev.tetokens;

import com.vk2gpz.tokenenchant.api.TokenEnchantAPI;
import lombok.NonNull;
import org.bukkit.entity.Player;
import org.insurgencedev.insurgencesets.api.ISetsAPI;
import org.insurgencedev.insurgencesets.api.currency.Currency;
import org.insurgencedev.insurgencesets.models.currency.TransactionTypes;

public class TokensCurrency extends Currency {

    private final TokenEnchantAPI tokenEnchantAPI;

    public TokensCurrency() {
        super("Tokens", "TE");
        tokenEnchantAPI = TokenEnchantAPI.getInstance();
    }

    @Override
    public boolean canAfford(@NonNull Player player, @NonNull Object o) {
        return tokenEnchantAPI.getTokens(player) >= ((Number) o).doubleValue();
    }

    @NonNull
    @Override
    public TransactionTypes handleDeposit(@NonNull Player player, @NonNull Object o, String s) {
        if (isInvalidSet(s)) {
            return TransactionTypes.FAIL;
        }

        tokenEnchantAPI.addTokens(player, ((Number) o).doubleValue());
        return TransactionTypes.SUCCESS;
    }

    @NonNull
    @Override
    public TransactionTypes handleTransaction(@NonNull Player player, @NonNull Object o, String s) {
        if (isInvalidSet(s)) {
            return TransactionTypes.FAIL;
        }

        double amount = ((Number) o).doubleValue();
        if (tokenEnchantAPI.getTokens(player) < amount) {
            return TransactionTypes.FAIL_INSUFFICIENT_FUNDS;
        }

        tokenEnchantAPI.removeTokens(player, amount);
        return TransactionTypes.SUCCESS;
    }
    
    private boolean isInvalidSet(String armorSet) {
        return armorSet == null || ISetsAPI.getArmorSetManager().findArmorSet(armorSet) == null;
    }
}
