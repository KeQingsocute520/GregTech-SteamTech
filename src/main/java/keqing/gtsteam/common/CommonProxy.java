package keqing.gtsteam.common;

import gregtech.api.GregTechAPI;
import gregtech.api.cover.CoverDefinition;
import keqing.gtsteam.common.covers.GTSCoverBehavior;
import keqing.gtsteam.loader.recipes.GTSRecipeManger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(
        modid = "gtsteam"
)
public class CommonProxy {
    public void init() {
        GTSRecipeManger.init();
    }
    @SubscribeEvent
    public static void registerCoverBehavior(GregTechAPI.RegisterEvent<CoverDefinition> event) {
        GTSCoverBehavior.init();
    }
}