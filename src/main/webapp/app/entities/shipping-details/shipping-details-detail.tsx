import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './shipping-details.reducer';

export const ShippingDetailsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const shippingDetailsEntity = useAppSelector(state => state.shippingDetails.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="shippingDetailsDetailsHeading">Shipping Details</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{shippingDetailsEntity.id}</dd>
          <dt>
            <span id="shippingAddress">Shipping Address</span>
          </dt>
          <dd>{shippingDetailsEntity.shippingAddress}</dd>
          <dt>
            <span id="shippingMethod">Shipping Method</span>
          </dt>
          <dd>{shippingDetailsEntity.shippingMethod}</dd>
          <dt>
            <span id="estimatedDeliveryDate">Estimated Delivery Date</span>
          </dt>
          <dd>
            {shippingDetailsEntity.estimatedDeliveryDate ? (
              <TextFormat value={shippingDetailsEntity.estimatedDeliveryDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>Order</dt>
          <dd>{shippingDetailsEntity.order ? shippingDetailsEntity.order.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/shipping-details" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/shipping-details/${shippingDetailsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ShippingDetailsDetail;
