# 2.0.0
* Make exclusion work in ``prometheus-metrics-exposition-formats`` 1.3.2+ #3
   * ``prometheus-metrics-exposition-formats`` is now directly included using shading
   * All protobuf code is removed during shading
   * This is a workaround until https://github.com/prometheus/client_java/pull/1190 is merged

# 1.0.0
_Initial release_
