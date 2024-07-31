package keqing.gtsteam.common;

import keqing.gtsteam.loader.recipes.GTSRecipeManger;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "gtsteam"
)
public class CommonProxy {
    public void init() {
        GTSRecipeManger.init();
    }

}