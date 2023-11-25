package org.insurgencedev.tetokens;

import org.bukkit.Bukkit;
import org.insurgencedev.insurgencesets.api.ISetsAPI;
import org.insurgencedev.insurgencesets.api.addon.ISetsAddon;
import org.insurgencedev.insurgencesets.api.addon.InsurgenceSetsAddon;

@ISetsAddon(name = "TE-Tokens", version = "1.0.0", author = "Insurgence Dev Team", description = "Use TokenEnchant's tokens as a currency")
public class TokenEnchantCurrencyAddon extends InsurgenceSetsAddon {

    @Override
    public void onAddonReloadablesStart() {
        if (Bukkit.getPluginManager().isPluginEnabled("TokenEnchant")) {
            ISetsAPI.getCurrencyManager().registerCurrency(new TokensCurrency());
        }
    }
}
