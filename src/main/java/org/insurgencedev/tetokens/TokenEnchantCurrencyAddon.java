package org.insurgencedev.tetokens;

import org.insurgencedev.insurgencesets.api.ISetsAPI;
import org.insurgencedev.insurgencesets.api.addon.ISetsAddon;
import org.insurgencedev.insurgencesets.api.addon.InsurgenceSetsAddon;
import org.insurgencedev.insurgencesets.libs.fo.Common;

@ISetsAddon(name = "TE-Tokens", version = "1.0.1", author = "Insurgence Dev Team", description = "Use TokenEnchant's tokens as a currency")
public class TokenEnchantCurrencyAddon extends InsurgenceSetsAddon {

    @Override
    public void onAddonReloadablesStart() {
        if (Common.doesPluginExist("TokenEnchant")) {
            ISetsAPI.getCurrencyManager().registerCurrency(new TokensCurrency());
        }
    }
}
