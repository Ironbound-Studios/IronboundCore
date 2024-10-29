package com.c446.ironbound_core.data.attachements;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.WrittenBookItem;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.UnknownNullability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class GenericAttachement implements INBTSerializable<CompoundTag> {
    public ArrayList<Integer> read_book = new ArrayList<>();

    @Override
    public @UnknownNullability CompoundTag serializeNBT(HolderLookup.Provider provider) {
        var tag = new CompoundTag();
        tag.putIntArray("books", read_book);


        return null;
    }

    public boolean addBook(ItemStack book){
        /**
         * tries to add a book to the collection--if it hasn't been read before.
         * */
        var data = book.get(DataComponents.WRITTEN_BOOK_CONTENT);
        var hash = data != null ? data.getPages(false).hashCode() : 0;
        if (book.getItem() instanceof WrittenBookItem bookItem && !read_book.contains(hash)){
            read_book.add((hash));
        }

        return false;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag tag) {
        var tagBooks = tag.getIntArray("books");
        for (int tagBook : tagBooks) {
            this.read_book.add(tagBook);
        }
    }
}
