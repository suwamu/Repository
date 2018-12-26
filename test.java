package testmod;

import net.minecraft.block.BlockBush;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod(modid = "testmod", version = "1.0", name = "testmod")
public class Testmod {
	
	
	public static BlockBush  FlowerBlock;
	
	

	
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		
		Item Tomato = new Item()
				.setCreativeTab(CreativeTabs.MATERIALS)
				.setUnlocalizedName("tomato")
				.setRegistryName("tomato");
		
		FlowerBlock = new BlockFlowerCloud();
		FlowerBlock.setCreativeTab(CreativeTabs.DECORATIONS);
		FlowerBlock.setUnlocalizedName("flowerblock");
		FlowerBlock.setRegistryName("flowerblock");
		
		
		

		ForgeRegistries.ITEMS.register(Tomato);
		ForgeRegistries.ITEMS.register(new ItemBlock(FlowerBlock).setRegistryName("testmod", "flowerblock"));
		
		ForgeRegistries.BLOCKS.register(FlowerBlock);

	

		
		if(event.getSide().isClient()) {
			
			RenderingRegistry.registerEntityRenderingHandler(EntitySample.class, new IRenderFactory<EntitySample> (){
				@Override
				public Render<? super EntitySample> createRenderFor(RenderManager manager){
					return new RenderSample(manager, new ModelSample(), 0.3f);
				}
			});
			
			
			
			RenderingRegistry.registerEntityRenderingHandler(EntitySample2.class, new IRenderFactory<EntitySample2> (){
				@Override
				public Render<? super EntitySample2> createRenderFor(RenderManager manager){
					return new RenderSample2(manager, new ModelSample2(), 0.3f);
				}
			});
			
			
			ModelLoader.setCustomModelResourceLocation(Tomato, 0, new ModelResourceLocation(new ResourceLocation("testmod", "tomato"), "inventory"));
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(FlowerBlock), 0, new ModelResourceLocation(new ResourceLocation("testmod", "flowerblock"), "inventory"));

		}
		
	}
	
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		EntityRegistry.registerModEntity(new ResourceLocation("sample"), EntitySample.class, "Sample", 0, this, 50, 1, true, 1000, 22);
		EntityRegistry.registerModEntity(new ResourceLocation("sample2"), EntitySample2.class, "Sample2", 1, this, 50, 1, true, 22, 1000);

	}

	
}
