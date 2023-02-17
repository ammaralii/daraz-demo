import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './payment-methods.reducer';

export const PaymentMethodsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const paymentMethodsEntity = useAppSelector(state => state.paymentMethods.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="paymentMethodsDetailsHeading">Payment Methods</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{paymentMethodsEntity.id}</dd>
          <dt>
            <span id="cardNumber">Card Number</span>
          </dt>
          <dd>{paymentMethodsEntity.cardNumber}</dd>
          <dt>
            <span id="cardHolderName">Card Holder Name</span>
          </dt>
          <dd>{paymentMethodsEntity.cardHolderName}</dd>
          <dt>
            <span id="expirationDate">Expiration Date</span>
          </dt>
          <dd>{paymentMethodsEntity.expirationDate}</dd>
          <dt>Customer</dt>
          <dd>{paymentMethodsEntity.customer ? paymentMethodsEntity.customer.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/payment-methods" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/payment-methods/${paymentMethodsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default PaymentMethodsDetail;
