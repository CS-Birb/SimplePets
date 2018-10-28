package simplepets.brainsynder.nms.entities.v1_12_R1.list;

import net.minecraft.server.v1_12_R1.DataWatcher;
import net.minecraft.server.v1_12_R1.DataWatcherObject;
import net.minecraft.server.v1_12_R1.DataWatcherRegistry;
import net.minecraft.server.v1_12_R1.World;
import simple.brainsynder.nbt.StorageTagCompound;
import simplepets.brainsynder.api.Size;
import simplepets.brainsynder.api.entity.passive.IEntityPolarBearPet;
import simplepets.brainsynder.api.pet.IPet;
import simplepets.brainsynder.nms.entities.v1_12_R1.AgeableEntityPet;

@Size(width = 1.3F, length = 1.4F)
public class EntityPolarBearPet extends AgeableEntityPet implements IEntityPolarBearPet {
    private static final DataWatcherObject<Boolean> IS_STANDING;

    static {
        IS_STANDING = DataWatcher.a(EntityPolarBearPet.class, DataWatcherRegistry.h);
    }

    public EntityPolarBearPet(World world) {
        super(world);
    }

    public EntityPolarBearPet(World world, IPet pet) {
        super(world, pet);
    }

    protected void registerDatawatchers() {
        super.registerDatawatchers();
        this.datawatcher.register(IS_STANDING, Boolean.FALSE);
    }

    @Override
    public StorageTagCompound asCompound() {
        StorageTagCompound object = super.asCompound();
        object.setBoolean("standing", isStanding());
        return object;
    }

    @Override
    public void applyCompound(StorageTagCompound object) {
        if (object.hasKey("standing"))
            setStandingUp(object.getBoolean("standing"));
        super.applyCompound(object);
    }

    public void setStandingUp(boolean flag) {
        this.datawatcher.set(IS_STANDING, flag);
    }

    @Override
    public boolean isStanding() {
        return this.datawatcher.get(IS_STANDING);
    }
}