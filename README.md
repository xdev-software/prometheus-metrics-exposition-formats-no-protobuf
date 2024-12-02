[![Latest version](https://img.shields.io/maven-central/v/software.xdev/prometheus-metrics-exposition-formats-no-protobuf?logo=apache%20maven)](https://mvnrepository.com/artifact/software.xdev/prometheus-metrics-exposition-formats-no-protobuf)
[![Build](https://img.shields.io/github/actions/workflow/status/xdev-software/prometheus-metrics-exposition-formats-no-protobuf/check-build.yml?branch=develop)](https://github.com/xdev-software/prometheus-metrics-exposition-formats-no-protobuf/actions/workflows/check-build.yml?query=branch%3Adevelop)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=xdev-software_prometheus-metrics-exposition-formats-no-protobuf&metric=alert_status)](https://sonarcloud.io/dashboard?id=xdev-software_prometheus-metrics-exposition-formats-no-protobuf)

# prometheus-metrics-exposition-formats-no-protobuf

Reconfigures [prometheus-metrics-exposition-formats](https://github.com/prometheus/client_java) to remove [protobuf](https://github.com/protocolbuffers/protobuf).

This module is designed to integrate with [Spring Boot Actuator (Prometheus)](https://docs.spring.io/spring-boot/api/rest/actuator/prometheus.html) or similar services.

### Why remove Protobuf?

1. [Prometheus Protobuf format is obsolete/experimental](https://github.com/prometheus/docs/blob/main/content/docs/instrumenting/exposition_formats.md#protobuf-format), the chance that it's used is near 0
2. The additional library can result in additional attack vectors. For example in [CVE-2024-7254](https://github.com/protocolbuffers/protobuf/security/advisories/GHSA-735f-pc8j-v9w8)
    * If the dependency is [reshaded](https://maven.apache.org/plugins/maven-shade-plugin/) it may not be detected by vulnerability scanners (as is the case in ``io.prometheus:prometheus-metrics-exposition-formats <= 1.3.1``)
3. The protobuf dependency is huge (around 10x bigger) in comparison to the other libraries

_See also [prometheus/client_java#1173](https://github.com/prometheus/client_java/issues/1173)_

## Installation
[Installation guide for the latest release](https://github.com/xdev-software/prometheus-metrics-exposition-formats-no-protobuf/releases/latest#Installation)

### Compatibility with ``io.prometheus:prometheus-metrics-exposition-formats``

| ``io.prometheus:prometheus-metrics-exposition-formats`` version | ``prometheus-metrics-exposition-formats-no-protobuf`` version |
| --- | --- |
| 1.3.4+ | [No longer required](https://prometheus.github.io/client_java/exporters/formats/#exclude-protobuf-exposition-format) |
| 1.3.2+ | ``2`` |
| < 1.3.2 | ``1`` |

### Spring Boot Actuator

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>...</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>

<dependencies>
    <dependency>
        <groupId>software.xdev</groupId>
        <artifactId>prometheus-metrics-exposition-formats-no-protobuf</artifactId>
        <version>...</version>
        <scope>runtime</scope>
    </dependency>

    <dependency>
        <groupId>io.micrometer</groupId>
        <artifactId>micrometer-registry-prometheus</artifactId>
        <exclusions>
            <!-- Exclude default module so that dependency is properly removed -->
            <exclusion>
                <groupId>io.prometheus</groupId>
                <artifactId>prometheus-metrics-exposition-formats</artifactId>
            </exclusion>
        </exclusions>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

## Support
If you need support as soon as possible and you can't wait for any pull request, feel free to use [our support](https://xdev.software/en/services/support).

## Contributing
See the [contributing guide](./CONTRIBUTING.md) for detailed instructions on how to get started with our project.

## Dependencies and Licenses
View the [license of the current project](LICENSE) or the [summary including all dependencies](https://xdev-software.github.io/prometheus-metrics-exposition-formats-no-protobuf/dependencies)
