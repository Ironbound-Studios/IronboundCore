import org.gradle.jvm.toolchain.JavaLanguageVersion.*
import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel

plugins {
    java
    idea
    `maven-publish`
    id("net.neoforged.moddev") version "2.0.15-beta"
}

val minecraftVersion: String by project
val minecraftVersionRange: String by project
val neoVersion: String by project
val neoVersionRange: String by project
val loaderVersionRange: String by project


val modId: String by project
val modName: String by project
val modLicense: String by project
val modVersion: String by project
val modGroupId: String by project
val modAuthors: String by project
val modDescription: String by project

val rhino_version: String by project
val irons_spells_version: String by project
val kubejsVersion: String by project
val player_animator_version: String by project

repositories {
    mavenLocal()
    maven("https://maven.saps.dev/minecraft")
    maven("https://code.redspace.io/releases")
    maven("https://code.redspace.io/snapshots")
    maven("https://maven.kosmx.dev/")
    maven("https://www.cursemaven.com")

//    maven{
//        url = uri("https://maven.pkg.github.com/IGN-Styly/arcanus")
//        credentials{
//            username = ""
//            password = ""
//        }
//    }
    maven {
        url=uri("https://jitpack.io")
        content {
            includeGroup("com.github.IGN-Styly")
        }
    }
    maven {
        // saps.dev Maven (KubeJS and Rhino)
        url = uri("https://maven.saps.dev/releases")
        content {
            includeGroup("dev.latvian.mods")
            includeGroup("dev.latvian.apps")
        }
    }
    maven {
        url = uri("https://jitpack.io")
        content {
            includeGroup("com.github.rtyley")
        }
    }
    maven {
        setUrl("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
        content {
            includeGroup("software.bernie.geckolib")
        }
    }
    flatDir {
        dir("libs")
    }
}

base {
    archivesName = modId
    version = modVersion
    group = modGroupId
}

neoForge {
    version = neoVersion
    validateAccessTransformers = true

    runs {
        register("gameTestServer") {
            server()
            systemProperty("neoforge.enabledGameTestNamespaces", modId)
        }
        register("client") {
            client()
        }
        register("data") {
            data()
        }
        register("server") {
            server()
        }
        configureEach {
            jvmArgument("-XX:+IgnoreUnrecognizedVMOptions")
            jvmArgument("-XX:+AllowEnhancedClassRedefinition")
            if (type.get() == "client") {
                programArguments.addAll("--width", "1920", "--height", "1080")
            }
        }
    }

    mods {
        register(modId) {
            sourceSet(sourceSets.main.get())
        }
    }
}

of(21).also { java.toolchain.languageVersion = it }

dependencies {
    //implementation("io.redspace:irons_spellbooks:$ironsSpellbooksVersion")
    runtimeOnly("dev.kosmx.player-anim:player-animation-lib-forge:${player_animator_version}")
    implementation("curse.maven:adorned-1036809:5546365") // curios-neoforge-9.0.5+1.21.0.jar
    runtimeOnly("curse.maven:caelus-308989:5442975") // caelus-neoforge-7.0.0+1.21.jar
    implementation("curse.maven:geckolib-388172:5605715") // geckolib-neoforge-1.21-4.5.8.jar
    runtimeOnly("curse.maven:emi-580555:5619579")
    runtimeOnly("curse.maven:jade-324717:5591256")
    implementation("curse.maven:apothic-attributes-898963:5530502")
    implementation("curse.maven:placebo-283644:5547234")
    //runtimeOnly("curse.maven:probejs-585406:5536459")
    implementation("io.redspace:irons_spellbooks:${irons_spells_version}")

    //you need uppercase
    implementation("com.github.IGN-Styly:Arcanus:1.21.1-0.3.6.1a")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("net.neoforged:testframework:$neoVersion")
}

tasks {
    processResources {
        val replaceProperties = mapOf(
            "minecraft_version" to minecraftVersion,
            "minecraft_version_range" to minecraftVersionRange,
            "neo_version" to neoVersion,
            "neo_version_range" to neoVersionRange,
            "loader_version_range" to loaderVersionRange,
            "mod_id" to modId,
            "mod_name" to modName,
            "mod_license" to modLicense,
            "mod_version" to modVersion,
            "mod_authors" to modAuthors,
            "mod_description" to modDescription,
            "irons_spellbooks_version" to irons_spells_version,
            "kubejs_version" to kubejsVersion
        )

        inputs.properties(replaceProperties)
        filesMatching(listOf("META-INF/neoforge.mods.toml")) {
            expand(replaceProperties)
        }
    }
    compileJava {
        options.encoding = "UTF-8"
    }
}

publishing {
    publications {
        register<MavenPublication>("mavenJava") {
            from(components.getByName("java"))
        }
    }
    repositories {
        maven("file://$projectDir/repo")
    }
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
    project {
        jdkName = "${java.sourceCompatibility}"
        languageLevel = IdeaLanguageLevel(java.sourceCompatibility)
    }
}