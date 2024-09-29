package keqing.gtsteam.common.metatileentities;

import gregtech.api.util.GTUtility;
import gregtech.common.metatileentities.multi.MetaTileEntityMultiblockTank;
import gregtech.common.metatileentities.multi.MetaTileEntityTankValve;
import keqing.gtsteam.common.metatileentities.multi.MetaTileEntityAlloykiln;
import keqing.gtsteam.common.metatileentities.multi.MetaTileEntityPrimitiveWaterPump;
import keqing.gtsteam.common.metatileentities.multi.steam.*;
import net.minecraft.util.ResourceLocation;

import static gregtech.common.metatileentities.MetaTileEntities.registerMetaTileEntity;
import static keqing.gtsteam.GTSteam.MODID;

public class GTSteamMetaTileEntities {
    public static MetaTileEntityAlloykiln ALLOY_KILN;
    public static MetaTileEntitySteamCompressor STEAM_COMPRESSOR;
    public static MetaTileEntitySteamExtractor STEAM_EXTRACTOR;
    public static MetaTileEntitySteamBlastFurnace STEAM_BLAST_FURNACE;
    public static MetaTileEntitySteamOreWasher STEAM_ORE_WASHER;
    public static MetaTileEntitySteamHammer STEAM_HAMMER;
    public static MetaTileEntitySteamMixer STEAM_MIXER;
    public static MetaTileEntityPrimitiveWaterPump WATER_PUMP;
    public static MetaTileEntitySteamCentrifuge STEAM_CENTRIFUGE;
    public static MetaTileEntityTankValve BRONZE_TANK_VALVE;
    public static MetaTileEntityMultiblockTank BRONZE_TANK;

    public static ResourceLocation gtsId(String id) {
        return new ResourceLocation(MODID, id);
    }
    public static int startID=29000;
    public static int getID()
    {
        startID++;
        return startID;
    }
    public static void initialization() {

        ALLOY_KILN = registerMetaTileEntity(getID(), new MetaTileEntityAlloykiln(gtsId("alloy_klin")));
        STEAM_COMPRESSOR = registerMetaTileEntity(getID(), new MetaTileEntitySteamCompressor(gtsId("steam_compressor")));
        STEAM_EXTRACTOR = registerMetaTileEntity(getID(), new MetaTileEntitySteamExtractor(gtsId("steam_extractor")));
        STEAM_BLAST_FURNACE = registerMetaTileEntity(getID(), new MetaTileEntitySteamBlastFurnace(gtsId("steam_blast_furnace")));
        STEAM_ORE_WASHER = registerMetaTileEntity(getID(), new MetaTileEntitySteamOreWasher(gtsId("steam_ore_washer")));
        STEAM_HAMMER = registerMetaTileEntity(getID(), new MetaTileEntitySteamHammer(gtsId("steam_hammer")));
        STEAM_CENTRIFUGE = registerMetaTileEntity(getID(), new MetaTileEntitySteamCentrifuge(gtsId("steam_centrifuge")));
        STEAM_MIXER = registerMetaTileEntity(getID(), new MetaTileEntitySteamMixer(gtsId("steam_mixer")));

        WATER_PUMP = registerMetaTileEntity(getID(), new MetaTileEntityPrimitiveWaterPump(gtsId("primitive_water_pump")));

        BRONZE_TANK_VALVE = registerMetaTileEntity(getID(), new MetaTileEntityTankValve(GTUtility.gregtechId("tank_valve.bronze"), true));
        BRONZE_TANK = registerMetaTileEntity(getID(), new MetaTileEntityMultiblockTank(GTUtility.gregtechId("tank.bronze"), true, 800000));
    }
}
