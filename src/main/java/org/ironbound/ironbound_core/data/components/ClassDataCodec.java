package org.ironbound.ironbound_core.data.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import org.ironbound.ironbound_core.ironbound_classes.ClassInstance;

public class ClassDataCodec {


    public static final Codec<ClassInstance> BASIC_CLASS_CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.STRING.fieldOf("main_class").forGetter(ClassInstance::classID),
                    Codec.STRING.fieldOf("sub_class").forGetter(ClassInstance::subClassID),
                    Codec.INT.fieldOf("level").forGetter(ClassInstance::level)
            ).apply(instance, ClassInstance::new)
    );

    public static final StreamCodec<ByteBuf, ClassInstance> CLASS_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, ClassInstance::classID,
            ByteBufCodecs.STRING_UTF8, ClassInstance::subClassID,
            ByteBufCodecs.INT, ClassInstance::level,
            ClassInstance::new
    );
}
