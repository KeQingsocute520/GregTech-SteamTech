package keqing.gtsteam;

import gregtech.common.ConfigHolder;
import keqing.gtsteam.client.ClientProxy;
import keqing.gtsteam.common.CommonProxy;
import keqing.gtsteam.common.item.GTSMetaitems;
import keqing.gtsteam.common.metatileentities.GTSteamMetaTileEntities;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = "gtsteam",
        name="GTSteam",
        acceptedMinecraftVersions = "[1.12.2,1.13)",
        version = "0.0.1-beta" ,
        dependencies = "required-after:gregtech@[2.8.8-beta,);"
)
public class GTSteam {
    public static final String PACK = "1.0.0";

    public static final String MODID = "gtsteam";
    public static final String NAME = "GTSteam";
    public static final String VERSION = "0.1";

    @Mod.Instance(GTSteam.MODID)
    public static GTSteam instance;

    @SidedProxy(
            clientSide = "keqing.gtsteam.client.ClientProxy",
            serverSide = "keqing.gtsteam.common.CommonProxy"
    )
    public static CommonProxy proxy;
    public static ClientProxy cproxy;
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        GTSMetaitems.initialization();
        GTSteamMetaTileEntities.initialization();
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();
    }
}
