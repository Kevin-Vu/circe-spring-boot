package com.circe.invoice.infrastructure.configuration;

import com.circe.invoice.infrastructure.repository.data.customer.CustomerEntity;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.event.spi.EnversPostUpdateEventListenerImpl;
import org.hibernate.event.spi.PostUpdateEvent;

public class CircePostUpdateEventListenerImpl extends EnversPostUpdateEventListenerImpl {

  public CircePostUpdateEventListenerImpl(EnversService enversService) {
    super(enversService);
  }

  @Override
  public void onPostUpdate(PostUpdateEvent event) {
    if (event.getEntity() instanceof CustomerEntity customer) {
      if (!StringUtils.isEmpty(customer.getAddress())) super.onPostUpdate(event);
    } else super.onPostUpdate(event);
  }
}
