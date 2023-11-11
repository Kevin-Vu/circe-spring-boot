package com.circe.invoice.configuration;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.envers.boot.internal.EnversIntegrator;
import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversListenerDuplicationStrategy;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class CirceEnversIntegrator extends EnversIntegrator {

  @Override
  public void integrate(
      Metadata metadata,
      SessionFactoryImplementor sessionFactory,
      SessionFactoryServiceRegistry serviceRegistry) {
    EnversService enversService = serviceRegistry.getService(EnversService.class);
    EventListenerRegistry listenerRegistry =
        serviceRegistry.getService(EventListenerRegistry.class);
    listenerRegistry.addDuplicationStrategy(EnversListenerDuplicationStrategy.INSTANCE);
    if (enversService.getEntitiesConfigurations().hasAuditedEntities()) {
      listenerRegistry.appendListeners(
          EventType.POST_UPDATE, new CircePostUpdateEventListenerImpl(enversService));
    }
  }
}
