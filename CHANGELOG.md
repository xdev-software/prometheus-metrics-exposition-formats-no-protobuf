# 3.0.1
* Migrated deployment to _Sonatype Maven Central Portal_ [#155](https://github.com/xdev-software/standard-maven-template/issues/155)
* Update dependencies

# 3.0.0
* In theory the library is [no longer required](https://prometheus.github.io/client_java/exporters/formats/#exclude-protobuf-exposition-format), however [there are still some problems with how the changes have been implemented upstream](https://github.com/xdev-software/prometheus-metrics-exposition-formats-no-protobuf/issues/27).
  * Library now completely removes protobuf functionality
    * ``PrometheusProtobufWriter`` is no longer loaded in the first place
  * Removed shading once again

# 2.0.0
* Make exclusion work in ``prometheus-metrics-exposition-formats`` 1.3.2+ #3
   * ``prometheus-metrics-exposition-formats`` is now directly included using shading
   * All protobuf code is removed during shading
   * This is a workaround until https://github.com/prometheus/client_java/pull/1190 is merged

# 1.0.0
_Initial release_
