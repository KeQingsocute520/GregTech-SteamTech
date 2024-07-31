package keqing.gtsteam.api.recipes;

import gregtech.api.gui.GuiTextures;
import gregtech.api.gui.widgets.ProgressWidget;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.FuelRecipeBuilder;
import gregtech.api.recipes.builders.PrimitiveRecipeBuilder;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;
import gregtech.core.sound.GTSoundEvents;

public class GTSRecipeMaps {
    public static final RecipeMap<SimpleRecipeBuilder> STEAM_BLAST_FURNACE_RECIPES;
    public static final RecipeMap<SimpleRecipeBuilder> STEAM_ORE_WASHER_RECIPES;
    public static final RecipeMap<PrimitiveRecipeBuilder> ALLOY_kILN;

    private GTSRecipeMaps() {}
    static {
        STEAM_BLAST_FURNACE_RECIPES = new RecipeMap<>("steam_blast_furnace",
                3, 1, 0, 0, new SimpleRecipeBuilder(), false);

        STEAM_ORE_WASHER_RECIPES = new RecipeMap<>("steam_ore_washer",
                3, 1, 0, 0, new SimpleRecipeBuilder(), false);

        ALLOY_kILN = new RecipeMap<>("alloy_klin", 2, 2, 0, 0, new PrimitiveRecipeBuilder(), false)
                .setProgressBar(GuiTextures.PROGRESS_BAR_MACERATE, ProgressWidget.MoveType.HORIZONTAL)
                .setSound(GTSoundEvents.MACERATOR);
    }
}
