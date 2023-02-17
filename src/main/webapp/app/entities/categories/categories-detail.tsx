import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './categories.reducer';

export const CategoriesDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const categoriesEntity = useAppSelector(state => state.categories.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="categoriesDetailsHeading">Entity translation missing for darazDemoApp.categories.detail.title</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{categoriesEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{categoriesEntity.name}</dd>
          <dt>
            <span id="detail">Detail</span>
          </dt>
          <dd>{categoriesEntity.detail}</dd>
        </dl>
        <Button tag={Link} to="/categories" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/categories/${categoriesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default CategoriesDetail;
