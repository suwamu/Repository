package testmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTomato extends Block{

	public BlockTomato() {
		super(Material.WOOD);
		setUnlocalizedName("block_tomato");
		setRegistryName("block_tomato");
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(!worldIn.isRemote)
		{
			double x = playerIn.getPosition().getX();
			double y = playerIn.getPosition().getY();
			double z = playerIn.getPosition().getZ();
			
			Teleport.TeleportDimension(playerIn, DimensionLibrary.LIBRARYID, x, y, z);
		}
		
		return true;
	}

}
