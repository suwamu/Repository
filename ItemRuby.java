package livingMinerals.ModItem;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemRuby extends Item {
	private int count=0;
	
	public ItemRuby(String name)
	{
		setRegistryName(name);
		setUnlocalizedName(name);
		setCreativeTab(CreativeTabs.MATERIALS);
		setMaxStackSize(1);
	}
	
	
	public static void regist(ItemStack stack,List<ItemStack> list)
	{
	
		ItemStack[] stacklist = list.toArray(new ItemStack[list.size()]);
		NBTTagList taglist = new NBTTagList();
		
		for(int i = 0; i < stacklist.length; i++)
		{
			NBTTagCompound nbt = new NBTTagCompound();
			stacklist[i].writeToNBT(nbt);
			taglist.appendTag(nbt);
		}
		
		NBTTagCompound tag = new NBTTagCompound();
		tag.setTag("TEST", taglist);
		
		stack.setTagCompound(tag);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		
		NBTTagList taglist = itemstack.getTagCompound().getTagList("TEST", 10);
		
		boolean deadflag = true;
		

		
		for(int i = 0; i < taglist.tagCount();i++)
		{
			ItemStack stack = new ItemStack(taglist.getCompoundTagAt(i));
			if(stack.getItem() != Item.getItemFromBlock(Blocks.AIR))
			{
				deadflag = false;
			}
			
			EntityItem item = new EntityItem(worldIn, playerIn.posX, playerIn.posY+3, playerIn.posZ, stack);
			ItemStack air = new ItemStack(Item.getItemFromBlock(Blocks.AIR));
			air.writeToNBT(taglist.getCompoundTagAt(i));
			if(!worldIn.isRemote)
			{
				worldIn.spawnEntity(item);
				System.out.println("*****************"+stack.toString());
				
				if(deadflag)
					itemstack = air;
			}
			
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
	}

/*
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		NBTTagCompound nbt = new NBTTagCompound();
		NBTTagList list = new NBTTagList();
		
		
		ItemStack is1 = new ItemStack(Items.DIAMOND);
		ItemStack is2 = new ItemStack(Items.BEEF);
		ItemStack is3 = new ItemStack(Items.IRON_INGOT);
		
		is1.writeToNBT(nbt);
		list.appendTag(nbt);
		
		is2.writeToNBT(nbt);
		list.appendTag(nbt);
		
		is3.writeToNBT(nbt);
		list.appendTag(nbt);
		
		NBTTagCompound nbt2 = new NBTTagCompound();
		nbt2.setTag("test", list);
		
		if(itemstack.getTagCompound() == null) 
		{
			itemstack.setTagCompound(nbt2);
		}else
		{
			NBTTagCompound nbt3 = itemstack.getTagCompound();
			NBTTagList list2 = nbt3.getTagList("test", 10);
			ItemStack is4 =  new ItemStack(list2.getCompoundTagAt(2));
			System.out.println("******************"+is4.toString());
			
		}
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
*/
	
 
}
