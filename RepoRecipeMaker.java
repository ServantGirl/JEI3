package ServantGirl.DumpsterDiving.handlers.jei;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ServantGirl.DumpsterDiving.init.blocks.Repuroser.RepoReci;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public final class RepoRecipeMaker {

	private RepoRecipeMaker() {
	}

	public static List<jeirepoRecipe> getFurnaceRecipes(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		RepoReci furnaceRecipes = RepoReci.instance();
		Map<ItemStack, ItemStack> smeltingMap = furnaceRecipes.getSmeltingList();

		List<jeirepoRecipe> recipes = new ArrayList<>();

		for (Map.Entry<ItemStack, ItemStack> entry : smeltingMap.entrySet()) {
			ItemStack input = entry.getKey();
			ItemStack output = entry.getValue();

			List<ItemStack> inputs = stackHelper.getSubtypes(input);
			jeirepoRecipe recipe = new jeirepoRecipe(inputs, output);
			recipes.add(recipe);
		}

		return recipes;
	}

}