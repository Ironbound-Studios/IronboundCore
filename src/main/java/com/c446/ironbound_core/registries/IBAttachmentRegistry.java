package com.c446.ironbound_core.registries;

import com.c446.ironbound_core.data.attachements.GenericAttachement;
import com.c446.ironbound_core.data.attachements.StatusAttachement;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static com.c446.ironbound_core.Ironbound.MODID;

public class IBAttachmentRegistry {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPE_DEFERRED_REGISTER = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MODID);

    public static final Supplier<AttachmentType<StatusAttachement>> STATUS_DATA = ATTACHMENT_TYPE_DEFERRED_REGISTER.register(
            "status_data_ib_core", () -> AttachmentType.serializable(StatusAttachement::new).build()
    );


    public static final Supplier<AttachmentType<GenericAttachement>> GENERIC_DATA = ATTACHMENT_TYPE_DEFERRED_REGISTER.register(
            "generic_data_ib_core", () -> AttachmentType.serializable(GenericAttachement::new).build()
    );
}
