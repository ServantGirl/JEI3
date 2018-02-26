package ServantGirl.DumpsterDiving.handlers.jei;

import java.util.Locale;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

import ServantGirl.DumpsterDiving.init.BlockInit;
import ServantGirl.DumpsterDiving.init.blocks.Repuroser.containtrashfurn;
import ServantGirl.DumpsterDiving.init.blocks.Repuroser.guitrasfurn;
import mezz.jei.Internal;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import mezz.jei.config.Constants;
import mezz.jei.plugins.vanilla.anvil.AnvilRecipeCategory;
import mezz.jei.plugins.vanilla.anvil.AnvilRecipeMaker;
import mezz.jei.plugins.vanilla.brewing.BrewingRecipeCategory;
import mezz.jei.plugins.vanilla.brewing.BrewingRecipeMaker;
import mezz.jei.plugins.vanilla.brewing.PotionSubtypeInterpreter;
import mezz.jei.plugins.vanilla.crafting.CraftingRecipeCategory;
import mezz.jei.plugins.vanilla.crafting.CraftingRecipeChecker;
import mezz.jei.plugins.vanilla.crafting.ShapedOreRecipeWrapper;
import mezz.jei.plugins.vanilla.crafting.ShapedRecipesWrapper;
import mezz.jei.plugins.vanilla.crafting.ShapelessRecipeWrapper;
import mezz.jei.plugins.vanilla.crafting.TippedArrowRecipeMaker;
import mezz.jei.plugins.vanilla.furnace.FuelRecipeMaker;
import mezz.jei.plugins.vanilla.furnace.FurnaceFuelCategory;
import mezz.jei.plugins.vanilla.furnace.FurnaceSmeltingCategory;
import mezz.jei.plugins.vanilla.furnace.SmeltingRecipeMaker;
import mezz.jei.plugins.vanilla.ingredients.FluidStackHelper;
import mezz.jei.plugins.vanilla.ingredients.FluidStackListFactory;
import mezz.jei.plugins.vanilla.ingredients.FluidStackRenderer;
import mezz.jei.plugins.vanilla.ingredients.ItemStackHelper;
import mezz.jei.plugins.vanilla.ingredients.ItemStackListFactory;
import mezz.jei.plugins.vanilla.ingredients.ItemStackRenderer;
import mezz.jei.startup.StackHelper;
import mezz.jei.transfer.PlayerRecipeTransferHandler;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

@JEIPlugin
public class jeiPluginDD implements IModPlugin {
	@Nullable
	private ISubtypeRegistry subtypeRegistry;

	@Override
	public void registerIngredients(IModIngredientRegistration ingredientRegistration) {
		Preconditions.checkState(this.subtypeRegistry != null);
		StackHelper stackHelper = Internal.getStackHelper();
		ItemStackListFactory itemStackListFactory = new ItemStackListFactory(this.subtypeRegistry);

		ingredientRegistration.register(ItemStack.class, itemStackListFactory.create(stackHelper), new ItemStackHelper(stackHelper), new ItemStackRenderer());
		ingredientRegistration.register(FluidStack.class, FluidStackListFactory.create(), new FluidStackHelper(), new FluidStackRenderer());
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		final IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
		registry.addRecipeCategories(
				new RepoRecipeCategory(guiHelper)
		);
	}

	@Override
	public void register(IModRegistry registry) {
		final IIngredientRegistry ingredientRegistry = registry.getIngredientRegistry();
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();

		registry.addRecipes(RepoRecipeMaker.getFurnaceRecipes(jeiHelpers), DDRecipeCategoryUid.REPURPOSER);

		registry.addRecipeClickArea(guitrasfurn.class, 78, 32, 28, 23, DDRecipeCategoryUid.REPURPOSER);

		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

		recipeTransferRegistry.addRecipeTransferHandler(containtrashfurn.class, DDRecipeCategoryUid.REPURPOSER, 1, 2, 4, 36);

		registry.addRecipeCatalyst(new ItemStack(BlockInit.trash_furn), DDRecipeCategoryUid.REPURPOSER);
	}
	public static final String MOD_ID = "dd";

	public static final String RESOURCE_DOMAIN = MOD_ID.toLowerCase(Locale.ENGLISH);
	public static final String TEXTURE_GUI_VANILLA = Constants.TEXTURE_GUI_PATH + "gui_vanilla.png";
	public static final ResourceLocation RECIPE_GUI_VANILLA = new ResourceLocation(RESOURCE_DOMAIN, TEXTURE_GUI_VANILLA);

}