package livingMinerals.BlockTomato;

import java.util.ArrayList;
import java.util.List;

import livingMinerals.Main;
import livingMinerals.ModItem.ItemRuby;
import livingMinerals.ModItem.ModItems;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockTomato extends BlockContainer{

	public BlockTomato() {
		super(Material.ROCK);
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		this.setRegistryName("block_tomato");
		this.setUnlocalizedName("block_tomato");
		this.setHardness(5.0F);
		this.setResistance(1.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityTomato();
	}

	@Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			TileEntityTomato te = (TileEntityTomato) world.getTileEntity(pos);
			IItemHandler itemHandler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side);
			if (!player.isSneaking()) {
				player.openGui(Main.instance, GuiHandler.BlockTomatoContainer, world, pos.getX(), pos.getY(), pos.getZ());
			} else {
				ItemStack stack = itemHandler.getStackInSlot(0);
				if (!stack.isEmpty()) {
					System.out.println(stack.getItem().getRegistryName()+" "+stack.getCount());
				} else {
					System.out.println("Empty");
				}
			}
		}
		return true;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
	TileEntityTomato te = (TileEntityTomato) world.getTileEntity(pos);
	IItemHandler handler =te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
	List<ItemStack> list = new ArrayList<ItemStack>();
	ItemStack itemstack = new ItemStack(ModItems.RUBY);

	
	
	for(int slot = 0; slot < handler.getSlots();slot++)
	{
		ItemStack stack = handler.getStackInSlot(slot);
		list.add(stack);
		
	}
		
		EntityItem item = new EntityItem(world, pos.getX(), pos.getY()+3, pos.getZ(), itemstack);
		ItemRuby.regist(itemstack, list);
		System.out.println("######################"+list.size());
		world.spawnEntity(item);
		super.breakBlock(world, pos, state);
	}
}