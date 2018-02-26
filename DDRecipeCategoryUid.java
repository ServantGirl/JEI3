package ServantGirl.DumpsterDiving.handlers.jei;

import mezz.jei.api.recipe.wrapper.ICustomCraftingRecipeWrapper;
import mezz.jei.api.recipe.wrapper.IShapedCraftingRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public final class DDRecipeCategoryUid {
		/**
		 * The crafting recipe category.
		 * <p>
		 * Automatically includes all {@link ShapedRecipes}, {@link ShapelessRecipes}, {@link ShapedOreRecipe}, and {@link ShapelessOreRecipe}.
		 * <p>
		 * To add a shaped recipe wrapper to this category, it must implement {@link IShapedCraftingRecipeWrapper}.
		 * <p>
		 * To override the normal behavior of the crafting recipe category, you can implement {@link ICustomCraftingRecipeWrapper}
		 */
		public static final String CRAFTING = "minecraft.crafting";

		/**
		 * The smelting recipe category.
		 * <p>
		 * Automatically includes everything from {@link FurnaceRecipes#getSmeltingList()}.
		 */
		public static final String REPURPOSER = "minecraft.smelting";

		/**
		 * The fuel recipe category.
		 * <p>
		 * Automatically includes everything that returns a value from {@link TileEntityFurnace#getItemBurnTime(ItemStack)}.
		 */
		public static final String FUEL = "minecraft.fuel";
}