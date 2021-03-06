/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.apm.collector.analysis.metric.provider.worker.instance.refmetric;

import org.apache.skywalking.apm.collector.analysis.metric.define.graph.MetricWorkerIdDefine;
import org.apache.skywalking.apm.collector.analysis.worker.model.impl.PersistenceWorker;
import org.apache.skywalking.apm.collector.analysis.worker.model.impl.PersistenceWorkerProvider;
import org.apache.skywalking.apm.collector.core.annotations.trace.GraphComputingMetric;
import org.apache.skywalking.apm.collector.core.module.ModuleManager;
import org.apache.skywalking.apm.collector.storage.StorageModule;
import org.apache.skywalking.apm.collector.storage.base.dao.IPersistenceDAO;
import org.apache.skywalking.apm.collector.storage.dao.irmp.IInstanceReferenceMinuteMetricPersistenceDAO;
import org.apache.skywalking.apm.collector.storage.table.instance.InstanceReferenceMetric;
import org.apache.skywalking.apm.collector.storage.table.instance.InstanceReferenceMetricTable;

/**
 * @author peng-yongsheng
 */
public class InstanceReferenceMinuteMetricPersistenceWorker extends PersistenceWorker<InstanceReferenceMetric> {

    private InstanceReferenceMinuteMetricPersistenceWorker(ModuleManager moduleManager) {
        super(moduleManager);
    }

    @Override public int id() {
        return MetricWorkerIdDefine.INSTANCE_REFERENCE_MINUTE_METRIC_PERSISTENCE_WORKER_ID;
    }

    @SuppressWarnings("unchecked")
    @Override protected IPersistenceDAO<?, ?, InstanceReferenceMetric> persistenceDAO() {
        return getModuleManager().find(StorageModule.NAME).getService(IInstanceReferenceMinuteMetricPersistenceDAO.class);
    }

    @Override protected boolean needMergeDBData() {
        return true;
    }

    public static class Factory extends PersistenceWorkerProvider<InstanceReferenceMetric, InstanceReferenceMinuteMetricPersistenceWorker> {

        public Factory(ModuleManager moduleManager) {
            super(moduleManager);
        }

        @Override public InstanceReferenceMinuteMetricPersistenceWorker workerInstance(ModuleManager moduleManager) {
            return new InstanceReferenceMinuteMetricPersistenceWorker(moduleManager);
        }

        @Override
        public int queueSize() {
            return 1024;
        }
    }

    @GraphComputingMetric(name = "/persistence/onWork/" + InstanceReferenceMetricTable.TABLE + "/minute")
    @Override protected void onWork(InstanceReferenceMetric input) {
        super.onWork(input);
    }
}
