package testmod;



import WorldGen.IWorldGenStoneToBrick;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = testmod.MODID, name = testmod.MODNAME, version = testmod.VERSION)

public class testmod {

	public static final String MODID = "testmod";
	public static final String MODNAME = "testmod";
	public static final String VERSION = "1.0.0";

	public static final  Block planks = new CostomBlockPlanks("planks");
	public static final Block log = new CostomBlockLog("log");
	public static final Block leaves = new CostomLeaves("leaves");
	public static final Block sapling = new CostomBlockSapling("sapling");

	public static final Block TOMATO = new BlockTomato();


	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

	}




	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		registerBlockWithVariants(planks, new ItemBlockVariants(planks));
		registerBlockWithVariants(log, new ItemBlockVariants(log));
		registerBlockWithVariants(leaves, new ItemBlockVariants(leaves));
		registerBlockWithVariants(sapling, new ItemBlockVariants(sapling));

		registerBlock(TOMATO);
		loadModel(TOMATO);
		

		GameRegistry.registerWorldGenerator(new WorldGenCustomTrees(), 0);
		GameRegistry.registerWorldGenerator(new IWorldGenStoneToBrick(), 0);

		DimensionInit.registerDimension();


		if(event.getSide().isClient()) {
			registerRenders();

		}

	}


	public static void registerRenders()
	{
		for(int i=0; i < CostomBlockPlanks.EnumType.values().length; i++)
		{
			registerRender(planks, i, "planks_"+ CostomBlockPlanks.EnumType.values()[i].getName());
			registerRender(log, i, "log_"+ CostomBlockPlanks.EnumType.values()[i].getName());
			registerRender(leaves, i, "leaves_"+ CostomBlockPlanks.EnumType.values()[i].getName());
			registerRender(sapling, i, "sapling_"+ CostomBlockPlanks.EnumType.values()[i].getName());
			System.out.println("***********************"+CostomBlockPlanks.EnumType.values()[i].getName());
		}
	}


	public static void registerBlockWithVariants(Block block, ItemBlock itemblock)
	{
		ForgeRegistries.BLOCKS.register(block);
		block.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		itemblock.setRegistryName(block.getRegistryName());
		ForgeRegistries.ITEMS.register(itemblock);
		System.out.println("*****************####################");
	}

	public static void registerBlock(Block block)
	{
		ItemBlock itemBlock = new ItemBlock(TOMATO);

		ForgeRegistries.BLOCKS.register(block);
		itemBlock.setRegistryName(block.getRegistryName());
		ForgeRegistries.ITEMS.register(itemBlock);
	}
	
	public static void loadModel(Block block)
	{
		String name = block.getRegistryName().toString();
		
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(MODID,name), "inventory"));
	}


	public static void registerRender(Block block, int meta, String filename)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(new ResourceLocation(MODID,filename),"inventory"));
	}



}
