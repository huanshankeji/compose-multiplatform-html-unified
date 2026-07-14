import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation

plugins {
    id("lib-conventions-without-abi-validation")
}

kotlin {
    @OptIn(ExperimentalAbiValidation::class)
    abiValidation()
}
