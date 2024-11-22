/*
 * Copyright © 2024 XDEV Software (https://xdev.software)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.prometheus.metrics.expositionformats;

import java.io.IOException;
import java.io.OutputStream;

import io.prometheus.metrics.model.snapshots.MetricSnapshots;


/**
 * Fork of original that removes Protobuf
 */
@SuppressWarnings("unused")
public class PrometheusProtobufWriter implements ExpositionFormatWriter
{
	@Override
	public boolean accepts(final String acceptHeader)
	{
		return false;
	}
	
	@Override
	public String getContentType()
	{
		return "";
	}
	
	@Override
	public void write(final OutputStream out, final MetricSnapshots metricSnapshots) throws IOException
	{
		// Do nothing
	}
}
