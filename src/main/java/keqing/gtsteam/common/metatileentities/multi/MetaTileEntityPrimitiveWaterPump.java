package keqing.gtsteam.common.metatileentities.multi;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import gregtech.api.gui.ModularUI;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.IPrimitivePump;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.unification.material.Materials;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.common.blocks.BlockMetalCasing;
import gregtech.common.blocks.MetaBlocks;
import gregtech.common.metatileentities.MetaTileEntities;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;
import gregtech.common.blocks.wood.BlockGregPlanks;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static gregtech.api.unification.material.Materials.Water;

public class MetaTileEntityPrimitiveWaterPump extends MultiblockControllerBase implements IPrimitivePump {

    private IFluidTank mudTank;
    private int biomeModifier = 0;
    private int hatchModifier = 0;

    public MetaTileEntityPrimitiveWaterPump(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        resetTileAbilities();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity tileEntity) {
        return new MetaTileEntityPrimitiveWaterPump(metaTileEntityId);
    }

    @Override
    public void update() {
        super.update();
        if (!getWorld().isRemote && getOffsetTimer() % 20 == 0 && isStructureFormed()) {
            if (biomeModifier == 0) {
                biomeModifier = getAmount();
            } else if (biomeModifier > 0) {
                mudTank.fill(Water.getFluid(getFluidProduction()), true);
            }
        }
    }

   private int getAmount() {
    BlockPos pos = getPos();
    if (pos == null) {
        return -1; // Disabled
    }

    World world = getWorld();
    if (world == null) {
        return -1; // Disabled
    }

    Biome biome = world.getBiome(pos);

    Set<BiomeDictionary.Type> biomeTypes = BiomeDictionary.getTypes(biome);
    if (pos.getY() < 40 || pos.getY() > 80) {
        return -1; // Disabled
    }

    if (!biomeTypes.contains(BiomeDictionary.Type.RIVER) && !biomeTypes.contains(BiomeDictionary.Type.SWAMP)) {
        return -1; // Disabled
    }

    return 250;
}

    @Override
    protected ModularUI createUI(EntityPlayer entityPlayer) {
        return null;
    }

    @Override
    protected boolean openGUIOnRightClick() {
        return false;
    }

    @Override
    protected void updateFormedValid() {

    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        resetTileAbilities();
    }

    private void initializeAbilities() {
        List<IFluidTank> tanks = getAbilities(MultiblockAbility.PUMP_FLUID_HATCH);
        if (tanks == null || tanks.isEmpty()) {
            tanks = getAbilities(MultiblockAbility.EXPORT_FLUIDS);
        }
        if (tanks == null || tanks.isEmpty()) {
            this.hatchModifier = 1;
        } else {
            this.hatchModifier = tanks.get(0).getCapacity() == 8000 ? 2 : 4;
            this.mudTank = tanks.get(0);
        }
    }

    private void resetTileAbilities() {
        this.mudTank = new FluidTank(0);
    }

    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("A   A", "A   A", "BBBBB", "A   A", "A   A", "BBBBB")
                .aisle("     ", "     ", "BBBBB", " CCC ", " CCC ", "BBBBB")
                .aisle("     ", "     ", "BBBBB", " CCC ", " CCC ", "BBBBB")
                .aisle("     ", "     ", "BBBBB", " CSC ", " CCC ", "BBBBB")
                .aisle("A   A", "A   A", "BBBBB", "A   A", "A   A", "BBBBB")
                .where('S', selfPredicate())
                .where('A', frames(Materials.TreatedWood))
                .where('B', states(MetaBlocks.PLANKS.getState(BlockGregPlanks.BlockType.TREATED_PLANK)))
                .where('C', states(MetaBlocks.METAL_CASING.getState(BlockMetalCasing.MetalCasingType.BRONZE_BRICKS))
                        .or(metaTileEntities(MetaTileEntities.FLUID_EXPORT_HATCH[0], MetaTileEntities.FLUID_EXPORT_HATCH[1], MetaTileEntities.PUMP_OUTPUT_HATCH).setExactLimit(1)))
                .where(' ', any())
                .build();
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart sourcePart) {
        return Textures.STEAM_CASING_BRONZE;
    }

    @Nonnull
    @Override
    protected ICubeRenderer getFrontOverlay() {
        return Textures.PRIMITIVE_PUMP_OVERLAY;
    }

    @Override
    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        this.getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), true, true);
    }

    @Override
    public String[] getDescription() {
        return Stream.of(
                new String[]{I18n.format("gregtech.multiblock.water_pump.description")}).toArray(String[]::new);
    }

    @Override
    public int getFluidProduction() {
        return biomeModifier * hatchModifier*100;
    }
}