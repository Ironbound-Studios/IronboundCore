package com.c446.ironbound_core.data.attachements;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.WrittenBookItem;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
import java.util.UUID;

public class GenericAttachement implements INBTSerializable<CompoundTag> {
    public ArrayList<UUID> read_book = new ArrayList<>();

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        return null;
    }

    public boolean addBook(ItemStack book){
        /**
         * tries to add a book to the collection--if it hasn't been read before.
         * */
        if (book.getItem() instanceof WrittenBookItem bookItem){
            
            bookItem.getName(book).toString();
        }


        return false;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {

    }
}
